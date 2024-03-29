<template>
  <div :class="{ 'Q-node': type == 'Q', 'M-node': type == 'M'}" :style="nodeStyle" 
    @mousedown="handleMousedown"
    @mouseover="handleMouseOver"
    @mouseleave="handleMouseLeave"
    v-bind:class="{selected: options.selected === id}">
    <div class="node-port node-input"
       @mousedown="inputMouseDown"
       @mouseup="inputMouseUp">
    </div>
    <div class="node-main">
      <div v-text="type + num" class="node-type"></div>
      <div v-text="label" class="node-label"></div>
      <div v-if="(count || count == 0) && type === 'Q'" v-text="'count: '+ count.toString()" class="node-label"></div>
    </div>
    <div class="node-port node-output" 
      @mousedown="outputMouseDown">
    </div>
    <div v-show="show.delete" class="node-delete">&times;</div>
  </div>
</template>

<script>
export default {
  name: 'FlowchartNode',
  props: {
    id: {
      type: Number,
      default: 1000,
      validator(val) {
        return typeof val === 'number'
      }
    },
    x: {
      type: Number,
      default: 0,
      validator(val) {
        return typeof val === 'number'
      }
    },    
    y: {
      type: Number,
      default: 0,
      validator(val) {
        return typeof val === 'number'
      }
    },
    type: {
      type: String,
      default: 'Default'
    },
    color: {
      type: String,
      default: '#e0e0e0'
    },
    count: {
      type: Number,
    },
    label: {
      type: String,
      default: 'input name'
    },
    options: {
      type: Object,
      default() {
        return {
          centerX: 1024,
          scale: 1,
          centerY: 140,
        }
      }
    },
    num: {
      type: Number,
    }
  },
  data() {
    return {
      show: {
        delete: false,
      }
    }
  },
  mounted() {
  },
  computed: {
    nodeStyle() {
      return {
        top: this.options.centerY + this.y * this.options.scale + 'px', // remove: this.options.offsetTop + 
        left: this.options.centerX + this.x * this.options.scale + 'px', // remove: this.options.offsetLeft + 
        transform: `scale(${this.options.scale})`,
      }
    }
  },
  methods: {
    handleMousedown(e) {
      const target = e.target || e.srcElement;
      // console.log(target);
      if (target.className.indexOf('node-input') < 0 && target.className.indexOf('node-output') < 0) {
        this.$emit('nodeSelected', e);
      }
      e.preventDefault();
    },
    handleMouseOver() {
      this.show.delete = true;
    },
    handleMouseLeave() {
      this.show.delete = false;
    },
    outputMouseDown(e) {
      this.$emit('linkingStart')
      e.preventDefault();
    },
    inputMouseDown(e) {
      e.preventDefault();
    },
    inputMouseUp(e) {
      this.$emit('linkingStop')
      e.preventDefault();
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="scss">
$themeColorM: v-bind(color); //'#' + Math.floor(Math.random()*16777215).toString(16); //: ;
$themeColorQ: rgb(211, 211, 4);
$portSize: 12;

.Q-node {
  //border-radius: 50%;
  margin: 0;
  width: 80px;
  height: 80px;
  position: absolute;
  box-sizing: border-box;
  box-shadow: 0 0 0 2px $themeColorQ;
  border: none;
  background: white;
  z-index: 1;
  opacity: .9;
  cursor: move;
  transform-origin: top left;
  .node-main {
    text-align: center;
    .node-type {
      background: $themeColorQ;
      color: white;
      font-size: 16px;
      padding: 5px;
    }
    .node-label {
      font-size: 13px;
    }
  }
  .node-port {
    position: absolute;
    width: #{$portSize}px;
    height: #{$portSize}px;
    left: 50%;
    transform: translate(-50%);
    border: 1px solid #ccc;
    border-radius: 100px;
    background: white;
    &:hover {
      background: $themeColorQ;
      border: 1px solid $themeColorQ;
    }
  }
  .node-input {
    top: 40px;
    left: 0px;
  }
  .node-output {
    bottom: 25px;
    left: 80px;
  }
  .node-delete {
    position: absolute;
    right: -6px;
    top: -6px;
    font-size: 12px;
    width: 12px;
    height: 12px;
    color: $themeColorQ;
    cursor: pointer;
    background: white;
    border: 1px solid $themeColorQ;
    border-radius: 100px;
    text-align: center;
    &:hover{
      background: $themeColorQ;
      color: white;
    }
  }
}
.M-node {
  //border-radius: 50%;
  margin: 0;
  width: 80px;
  height: 80px;
  position: absolute;
  box-sizing: border-box;
  box-shadow: 0 0 0 2px $themeColorM;
  border: none;
  background: white;
  z-index: 1;
  opacity: .9;
  cursor: move;
  transform-origin: top left;
  border-radius:35px;
  .node-main {
    text-align: center;
    .node-type {
      background: $themeColorM;
      color: black;
      font-size: 13px;
      padding: 6px;
      border-top-left-radius: 35px;
      border-top-right-radius: 35px;
      font-size: 16px;
      padding: 5px;
    }
    .node-label {
      font-size: 13px;
    }
  }
  .node-port {
    position: absolute;
    width: #{$portSize}px;
    height: #{$portSize}px;
    left: 50%;
    transform: translate(-50%);
    border: 1px solid #ccc;
    border-radius: 100px;
    background: white;
    &:hover {
      background: $themeColorM;
      border: 1px solid $themeColorM;
    }
  }
  .node-input {
    top: 40px;
    left: 0px;
  }
  .node-output {
    bottom: 25px;
    left: 80px;
  }
  .node-delete {
    position: absolute;
    right: -6px;
    top: -6px;
    font-size: 12px;
    width: 12px;
    height: 12px;
    color: $themeColorM;
    cursor: pointer;
    background: white;
    border: 1px solid $themeColorM;
    border-radius: 100px;
    text-align: center;
    &:hover{
      background: $themeColorM;
      color: white;
    }
  }
}
.selected {
  box-shadow: 0 0 0 2px rgb(255, 136, 85);
}
</style>
